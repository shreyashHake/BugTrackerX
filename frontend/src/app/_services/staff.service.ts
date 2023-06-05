import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StaffService {
  PATH_OF_API = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

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
}
