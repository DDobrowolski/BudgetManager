import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from '../service/authservice.service';
import { Router } from '../../../node_modules/@angular/router';
import { User } from '../model/User';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  user: User = new User();
  credentials = {username: "", password: ""};
  constructor(private authService: AuthserviceService, private router: Router) {
    if (this.authService.isLogged){
      router.navigate(['budget']);
    }
   }

  ngOnInit() {
  
  }

  login() {
    this.authService.logIn(this.credentials, () => {
        window.location.href = '/budget';
    });
    return false;
  }

}
