import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  public getAllProject(): Observable<Project[]> {
    return this.httpClient.get<Project[]>(`${this.PATH_OF_API}/getProjects`);
  }

  public saveProject(project: any): Observable<any> {
  const headers = new HttpHeaders({
    Authorization: 'Bearer ' + this.userAuthUser.getToken() 
  });

  return this.httpClient.post(`${this.PATH_OF_API}/addProject`, project, { headers });
}
}
