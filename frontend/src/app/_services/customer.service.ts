import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  PATH_OF_API = "http://localhost:8080";

  constructor(
    private httpClient: HttpClient
    ) { }
    
}
