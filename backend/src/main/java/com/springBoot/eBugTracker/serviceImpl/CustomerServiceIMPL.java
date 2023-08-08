package com.springBoot.eBugTracker.serviceImpl;

import com.springBoot.eBugTracker.dtos.bugs.BugDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProfileDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.entity.Otp;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.repository.IUserRepository;
import com.springBoot.eBugTracker.repository.OtpRepo;
import com.springBoot.eBugTracker.repository.bugs.BugProcessRepo;
import com.springBoot.eBugTracker.repository.bugs.BugRepo;
import com.springBoot.eBugTracker.repository.customer.CustomerProfileRepo;
import com.springBoot.eBugTracker.repository.customer.CustomerProjectRepo;
import com.springBoot.eBugTracker.service.CustomerService;
import com.springBoot.eBugTracker.util.DtoHelper;
import com.springBoot.eBugTracker.util.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private CustomerProfileRepo customerProfileRepo;
    @Autowired
    private CustomerProjectRepo customerProjectRepo;
    @Autowired
    private BugRepo bugRepo;
    @Autowired
    private BugProcessRepo bugProcessRepo;
    @Autowired
    private DtoHelper dtoHelper;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private OtpRepo otpRepo;

    @Override
    public CustomerProfileDTO createCustomerProfile(CustomerProfile customerProfile) {
        log.info("Inside:- /CustomerServiceIMPL/createCustomerProfile");
        try {
            customerProfile.setCreatedDate(LocalDate.now());
            CustomerProfile customerProfile1 = customerProfileRepo.save(customerProfile);

            int otp = new Random().nextInt(900000) + 100000;
            String body = "Dear " + customerProfile1.getCustomerName() + " ,\n Thank you for registering with E-Bug Tracker!" +
                    " To ensure the security of your account and complete " +
                    "the registration process, we kindly ask you to verify your email address by clicking on the link below:\n" +
                    " http://localhost:8080/customer/verifyEmail/" + customerProfile1.getUser().getUserName() + "/" + otp + "\n" +
                    " By verifying your email address, you will be able to access all the features and benefits of our platform.\n" +
                    "\n" +
                    "If you did not create an account on our platform or received this email in error, please disregard it. No further action is necessary.\n" +
                    "\n" +
                    "If you have any questions or need further assistance, please don't hesitate to contact our support team at ebugtracker.com.\n" +
                    "\n" +
                    "Thank you for choosing E-Bug Tracker!\n" +
                    "\n" +
                    "Best regards,\n" +
                    "E-Bug Tracker\n" +
                    "Customer Support Team\n" +
                    "\n" +
                    "Note: For security reasons, never ask users to provide sensitive information, such as passwords, through email verification links. The verification link should only be used to confirm the validity of the provided email address.";

            mailSenderService.sendMail(customerProfile1.getUser().getUserName(),
                    "E-Bug Tracker : Account Verification - Action Required ",
                    body);

            Otp otp1 = new Otp(
                    customerProfile1.getUser().getUserName(),
                    otp,
                    LocalDate.now()
            );
            otpRepo.save(otp1);

            return dtoHelper.getCustomerProfileDto(customerProfile1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @Override
    public CustomerProjectDTO createCustomerProject(CustomerProject customerProject) {
        log.info("Inside:- /CustomerServiceIMPL/createCustomerProject");
        try {
            Optional<CustomerProfile> optional = customerProfileRepo.findById(customerProject.getCustomerProfile().getCustomerProfileId());
            if (optional.isPresent()) {
                CustomerProfile customerProfile = optional.get();
                customerProject.setCustomerProfile(customerProfile);
                customerProject.setCreatedDate(LocalDate.now());
                return dtoHelper.getCustomerProjectDto(customerProjectRepo.save(customerProject));
            }
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
        return new CustomerProjectDTO();
    }

    @Override
    public BugDTO addBug(Bug bug) {
        log.info("Inside:- /CustomerServiceIMPL/addBug");
        try {
            Optional<CustomerProject> optional = customerProjectRepo.findById(bug.getCustomerProject().getProjectId());
            if (optional.isPresent()) {
                CustomerProject customerProject = optional.get();
                bug.setCustomerProject(customerProject);
                bug.setCreatedDate(LocalDate.now());
                BugDTO bugDTO = dtoHelper.getBugDto(bugRepo.save(bug));
                BugProcess bugProcess = new BugProcess();
                bugProcess.setGlobalStatus("Waiting for staff");
                bugProcess.setBug(bug);
                bugProcessRepo.save(bugProcess);
                return bugDTO;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new BugDTO();
    }

    @Override
    public CustomerProfileDTO getCustomerProfile(String userName) {
        log.info("Inside:- /CustomerServiceIMPL/getCustomerProfile");
        try {
            Optional<User> userOptional = iUserRepository.findById(userName);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return dtoHelper.getCustomerProfileDto(customerProfileRepo.findByUser(user));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @Override
    public List<CustomerProjectDTO> getCustomerProjects(int profileId) {
        log.info("Inside:- /CustomerServiceIMPL/getCustomerProjects");
        try {
            Optional<CustomerProfile> customerProfileOptional = customerProfileRepo.findById(profileId);
            if (customerProfileOptional.isPresent()) {
                CustomerProfile customerProfile = customerProfileOptional.get();
                List<CustomerProjectDTO> customerProjectDTOS = new ArrayList<>();
                for (CustomerProject customerProject :
                        customerProjectRepo.findByCustomerProfile(customerProfile)) {
                    customerProjectDTOS.add(dtoHelper.getCustomerProjectDto(customerProject));
                }
                return customerProjectDTOS;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<BugDTO> getBugs(int projectId) {
        log.info("Inside:- /CustomerServiceIMPL/getBugs");
        try {
            Optional<CustomerProject> optional = customerProjectRepo.findById(projectId);
            if (optional.isPresent()) {
                CustomerProject customerProject = optional.get();
                List<BugDTO> bugDTOS = new ArrayList<>();
                for (Bug bug :
                        bugRepo.findByCustomerProject(customerProject)) {
                    bugDTOS.add(dtoHelper.getBugDto(bug));
                }
                return bugDTOS;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean haveCustomerProfile(String username) {
        log.info("Inside:- /CustomerServiceIMPL/haveCustomerProfile");
        try {
            Optional<User> user = iUserRepository.findById(username);
            if (user.isEmpty()) {
                return false;
            }
            CustomerProfile customerProfile = customerProfileRepo.findByUser(user.get());
            return customerProfile != null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerProfileDTO> getAllCustomer() {
        log.info("Inside:- /CustomerServiceIMPL/getAllCustomer");
        try {
            List<CustomerProfileDTO> customerProfileDTOS = new ArrayList<>();
            for (CustomerProfile customerProfile : customerProfileRepo.findAll()) {
                customerProfileDTOS.add(dtoHelper.getCustomerProfileDto(customerProfile));
            }
            return customerProfileDTOS;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public CustomerProfileDTO getCustomerProfileById(int customerId) {
        log.info("Inside:- /CustomerServiceIMPL/getCustomerProfileById");
        try {
            Optional<CustomerProfile> optional = customerProfileRepo.findById(customerId);
            if (optional.isPresent()) {
                return dtoHelper.getCustomerProfileDto(optional.get());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @Override
    public RedirectView verifyEmail(String email, int token) {
        log.info("Inside:- /CustomerServiceIMPL/verifyEmail");
        try {
            Optional<Otp> optionalOtp = otpRepo.findById(email);
            if (optionalOtp.isPresent()) {
                Otp otp1 = optionalOtp.get();
                if (otp1.getOtp() == token) {
                    Optional<User> user = iUserRepository.findById(email);
                    if (user.isPresent()) {
                        CustomerProfile customerProfile = customerProfileRepo.findByUser(user.get());
                        customerProfile.setIsActive(true);
                        customerProfileRepo.save(customerProfile);
                        return new RedirectView("http://localhost:4200/verify_profile/true");
                    }
                } else {
                    return new RedirectView("http://localhost:4200/verify_profile/false");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new RedirectView();
    }


}
