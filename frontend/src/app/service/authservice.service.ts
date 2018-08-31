import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService {

  public isLogged = false;

  constructor(private http: HttpClient) {
    this.isLogged = JSON.parse(localStorage.getItem('isLogged'));
  }

  logIn(credentials, callback) {

    const headers = new HttpHeaders(credentials ? {
        authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('user', {headers: headers}).subscribe(response => {
        if (response['name']) {
            this.isLogged=true;
            let logged = this.isLogged.toString();
            localStorage.setItem("isLogged", logged);
        } else {
            this.isLogged = false;
        }
        return callback && callback();
    });
  }

  logOut() {
    return this.http.post("/logout", {})
    .pipe(map((response: Response) =>{
      localStorage.removeItem('isLogged');
      this.isLogged = false;
    }));
  }
}
