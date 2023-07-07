import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerProfile } from '../_model/customerProfile.model';
import { Observable, catchError, of } from 'rxjs';
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

  // getCustomerProfile()  : Observable<any> {
  //   const userName = this.userAuthService.getUserName();
  //   return this.httpClient.get(`${this.PATH_OF_API}/customer/getCustomerProfile/${userName}`);
  // }

  getCustomerProfile(): Observable<any> {
    const userName = this.userAuthService.getUserName();
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getCustomerProfile/${userName}?fetchUserRole=true`);
  }
  

  // getCustomerProfileId(profile : any):Number{
  //    let projectId : Number;
  //    this.getCustomerProfile().subscribe(
  //     (res) => {
  //         this.projectId
  //     }
  //   )
  // }


    
}
