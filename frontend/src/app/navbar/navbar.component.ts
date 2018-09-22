import { Component, OnInit } from '@angular/core';
import { AuthserviceService } from '../service/authservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    isLogged = false;
  constructor(private authService: AuthserviceService, private router: Router) {

   }

  ngOnInit() {
    this.isLogged = this.authService.isLogged;
  }


    logOut(){
      if(confirm("Do you want to logout?")){
      this.authService.logOut()
      .subscribe(
        data => {
          this.router.navigateByUrl('/login');
          this.ngOnInit();
        },
        error => {
          return;
        }
      )
    } else return;
  }
}
