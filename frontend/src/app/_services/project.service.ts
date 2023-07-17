import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Project } from '../_model/project.model';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  PATH_OF_API = "http://localhost:8080";

  constructor(
    private httpClient: HttpClient,
    private userAuthUser: UserAuthService
  ) { }
  
  
  public getAllProject(profileId : number): Observable<Project[]> {
    return this.httpClient.get<Project[]>(`${this.PATH_OF_API}/customer/getCustomerProjects/${profileId}`);
  }

  public saveProject(project: any): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/customer/createCustomerProject`, project);
  }

}
