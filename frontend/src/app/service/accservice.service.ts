import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { User } from '../model/User';


@Injectable({
  providedIn: 'root'
})
export class AccserviceService {

  constructor(public http: Http) { }

  createAccount(user:User){
    return this.http.post('/register', user)
    .pipe(map(resp=>resp.json()));
  }
}
