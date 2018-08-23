import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  constructor(private http: HttpClient) { }

  logIn(credentials, callback){
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic' + btoa(credentials.username + ':' + credentials.password)
    }: {});

    this.http.get('user', {headers: headers}).subscribe(response => {
      if (response['name']){
        
      }
      return callback && callback();
    })
  }
}
