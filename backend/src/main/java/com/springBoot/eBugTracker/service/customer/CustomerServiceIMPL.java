package com.springBoot.eBugTracker.service.customer;

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
import com.springBoot.eBugTracker.util.DtoHelper;
import com.springBoot.eBugTracker.util.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        customerProfile.setCreatedDate(LocalDate.now());
        CustomerProfile customerProfile1 = customerProfileRepo.save(customerProfile);

//        int otp = new Random().nextInt(900000) + 100000;
//        String body = "Hello "+customerProfile1.getCustomerName()+" ,\n Your OTP for email verification is "+otp+ " .\n ";
//        mailSenderService.sendMail(customerProfile1.getUser().getUserName(),
//                "E-Bug Tracker : Verify Mail ",
//                body);
//        Otp otp1 = new Otp(
//                customerProfile1.getUser().getUserName(),
//                otp
//        );
//        otpRepo.save(otp1);

        return dtoHelper.getCustomerProfileDto(customerProfile1);
    }

    @Override
    public CustomerProjectDTO createCustomerProject(CustomerProject customerProject) {
//        System.out.println("Customer Project : " +customerProject);
        CustomerProfile customerProfile = customerProfileRepo.findById(customerProject.getCustomerProfile().getCustomerProfileId()).get();
        customerProject.setCustomerProfile(customerProfile);
        customerProject.setCreatedDate(LocalDate.now());
//        System.out.println("Customer Project 1 : " +customerProject);
        return dtoHelper.getCustomerProjectDto(customerProjectRepo.save(customerProject));
    }

    @Override
    public BugDTO addBug(Bug bug) {
        System.out.println("Bug 1 : " + bug);
        System.out.println("pro id : " + bug.getCustomerProject().getProjectId());

        CustomerProject customerProject = customerProjectRepo.findById(bug.getCustomerProject().getProjectId()).get();
//        System.out.println("Customer Project 1 : " +customerProject);
        bug.setCustomerProject(customerProject);
        bug.setCreatedDate(LocalDate.now());
        BugDTO bugDTO = dtoHelper.getBugDto(bugRepo.save(bug));
//        System.out.println("Bug 2 : "+ bug);
        BugProcess bugProcess = new BugProcess();
        bugProcess.setGlobalStatus("Waiting for staff");
        bugProcess.setBug(bug);
        bugProcessRepo.save(bugProcess);

        return bugDTO;
    }

    @Override
    public CustomerProfileDTO getCustomerProfile(String userName) {
        User user = iUserRepository.findById(userName).get();
//        System.out.println("USer : "+user);
        return dtoHelper.getCustomerProfileDto(customerProfileRepo.findByUser(user));
    }

    @Override
    public List<CustomerProjectDTO> getCustomerProjects(int profileId) {
        CustomerProfile customerProfile = customerProfileRepo.findById(profileId).get();
//        System.out.println("CustomerProfile : "+customerProfile);
        List<CustomerProjectDTO> customerProjectDTOS = new ArrayList<>();
        for (CustomerProject customerProject :
                customerProjectRepo.findByCustomerProfile(customerProfile)) {
            customerProjectDTOS.add(dtoHelper.getCustomerProjectDto(customerProject));
        }
        return customerProjectDTOS;
    }

    @Override
    public List<BugDTO> getBugs(int projectId) {
        CustomerProject customerProject = customerProjectRepo.findById(projectId).get();
        List<BugDTO> bugDTOS = new ArrayList<>();
        for (Bug bug :
                bugRepo.findByCustomerProject(customerProject)) {
            bugDTOS.add(dtoHelper.getBugDto(bug));
        }
        return bugDTOS;
    }

    @Override
    public boolean haveCustomerProfile(String username) {
        Optional<User> user = iUserRepository.findById(username);
        if (user.isEmpty()) {
            return false;
        }
        CustomerProfile customerProfile = customerProfileRepo.findByUser(user.get());
        return customerProfile != null;
    }

    @Override
    public List<CustomerProfileDTO> getAllCustomer() {
        List<CustomerProfileDTO> customerProfileDTOS = new ArrayList<>();
        for (CustomerProfile customerProfile : customerProfileRepo.findAll()) {
            customerProfileDTOS.add(dtoHelper.getCustomerProfileDto(customerProfile));
        }
        return customerProfileDTOS;
    }

    @Override
    public CustomerProfileDTO getCustomerProfileById(int customerId) {
        return dtoHelper.getCustomerProfileDto(customerProfileRepo.findById(customerId).get());
    }


}
