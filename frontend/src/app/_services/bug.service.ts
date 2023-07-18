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

  public getBugDetails(bugId : number) : Observable<any>{
    return this.httpClient.get(`${this.PATH_OF_API}/bug/getBugDetails/${bugId}`);
  }

  public getBugsByStaff(staff_id : number){
    return this.httpClient.get(`${this.PATH_OF_API}/staff/getBugsByStaff/${staff_id}`);
  }

  public assignStaffToBug(staff_id:number,bug_process_id :number){
    return this.httpClient.put(`${this.PATH_OF_API}/bug/assignStaff/${bug_process_id}`,staff_id);
  }

  public addCommentToBug(comment:any){
    return this.httpClient.post(`${this.PATH_OF_API}/bug/addComment/`,comment);
  }
}
