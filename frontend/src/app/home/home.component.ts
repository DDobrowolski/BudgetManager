import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from '../service/authservice.service';
import { Router } from '../../../node_modules/@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User=new User();
  errorMessage:string;
  credentials = {username: "", password: ""};
  constructor(private authService: AuthserviceService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.authService.logIn(this.credentials, () => {
        this.router.navigateByUrl('/');
    });
    return false;
  }

}
