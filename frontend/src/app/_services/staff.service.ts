import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { User } from '../_model/user.model';
import { StaffProfile } from '../_model/staffProfile.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {
  PATH_OF_API = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  public registerStaff(user: any): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/createNewStaff`, user);
  }

  public deleteStaffUser(userName: string) {
    return this.httpClient.delete(`${this.PATH_OF_API}/deleteStaff/${userName}`);
  }

  public getUserByUserName(userName : string): Observable<User> {
    return this.httpClient.get<User>(`${this.PATH_OF_API}/getUserByUserName/${userName}`);
  }

  public updateUser(user: any) {
    return this.httpClient.patch(`${this.PATH_OF_API}/updateUser`, user);
  }

  // new one

  public createStaffProfile(profile : StaffProfile) : Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/staff/createStaffProfile`, profile);
  }

  public getAllStaff() : Observable<StaffProfile[]> {
    return this.httpClient.get<StaffProfile[]>(`${this.PATH_OF_API}/staff/getAllStaff`);
  }

  public getStaffProfile(username : string){
    return this.httpClient.get(`${this.PATH_OF_API}/staff/getStaffProfile/${username}`);
  }

  public getStaffProfileById(staff_id : number){
    return this.httpClient.get(`${this.PATH_OF_API}/staff/getStaffProfileById/${staff_id}`);
  }
  
}
