import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerProfile } from '../_model/customerProfile.model';
import { BehaviorSubject, Observable, catchError, of } from 'rxjs';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  PATH_OF_API = "http://localhost:8080";

  constructor(
    private httpClient: HttpClient,
    private userAuthService : UserAuthService
    ) { }

    private customerSubject = new BehaviorSubject<any>(null);
    customer$ = this.customerSubject.asObservable();
  
    setCustomer(customer: any) {
      this.customerSubject.next(customer);
    }

  completeProfile(profile: CustomerProfile): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/customer/createCustomerProfile/`, profile)
      .pipe(
        catchError(error => {
          console.log(error);
          alert('Database is not connected');
          return of(null);
        })
      )
  }

  getCustomerProfile()  : Observable<any> {
    const userName = this.userAuthService.getUserName();
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getCustomerProfile/${userName}`);
  }

  getAllCustomer() : Observable<any> {
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getAllCustomer`);
  }

  profileId !: number;

  getProfileId() : number {
    return this.profileId;
  }

  setProfileId(id : number) {
    this.profileId = id;
  }

  getCustomerProfiles() : Observable<any>{
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getAllCustomer`);
  }

  getCustomerProfileById(customer_id : number) : Observable<any> {
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getCustomerProfileById/${customer_id}`)
  }

}
