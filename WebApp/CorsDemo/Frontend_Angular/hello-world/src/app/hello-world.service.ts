import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {
  private backendUrl = 'http://localhost:8080/api/hello';

  constructor(private http: HttpClient) {}

  getHelloMessage(): Observable<string> {
    console.log("Inside getHelloMessage");
    return this.http.get(this.backendUrl, { responseType: 'text' });
  }
}
