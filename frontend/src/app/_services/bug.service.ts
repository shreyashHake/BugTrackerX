import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BugService {
  PATH_OF_API = "http://localhost:8080";

  constructor(
    private httpClient: HttpClient,
  ) { }

  public addBug(bug: any): Observable<any> {
    return this.httpClient.post(`${this.PATH_OF_API}/customer/addBug`, bug);
  }
  public getBugs(projectId : number) : Observable<any> {
    return this.httpClient.get(`${this.PATH_OF_API}/customer/getBugs/${projectId}`);
  }
}
