import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  PATH_OF_API = "http://localhost:8080";
  requestHeader = new HttpHeaders({
    "No-Auth": "True"
  });

  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthService
  ) { }

  public login(loginData: any) {
    return this.httpClient.post(`${this.PATH_OF_API}/authenticate`, loginData, { headers: this.requestHeader })
  }

  public registerCustomer(user: any): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/createNewUser`, user)
      .pipe(
        catchError(error => {
          console.log(error);
          alert('Database is not connected');
          return of(null);
        })
      )
  }

  public registerStaff(user: any): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/createNewStaff`, user)
      .pipe(
        catchError(error => {
          console.log(error);
          alert('Database is not connected');
          return of(null);
        })
      )
  }

  public forUser() {
    return this.httpClient.get(this.PATH_OF_API + '/forUser', {
      responseType: 'text',
    });
  }


  public forAdmin() {
    return this.httpClient.get(this.PATH_OF_API + '/forAdmin', {
      responseType: 'text',
    });
  }


  public roleMatch(allowedRoles: any): boolean {
    let isMathch = false;
    const role = this.userAuthService.getRole();

    if (role != null && role) {
      for (let i = 0; i < allowedRoles.length; i++) {
        if (role == allowedRoles[i]) {
          isMathch = true;
          return isMathch;
        } else {
          return isMathch;
        }
      }
    }

    return isMathch;
  }

  // for removing 'sign-up' from 'Admin' and 'Staff'

  private isCustomer = false;

  setIsCustomer(value: boolean): void {
    this.isCustomer = value;
  }

  getIsCustomer(): boolean {
    return this.isCustomer;
  }

}
