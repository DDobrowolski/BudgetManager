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
    this.isLogged = this.authService.isLogged;
    console.log(this.authService.isLogged);
   }

  ngOnInit() {

  }


  logOut(){
    this.authService.logOut()
    .subscribe(
      data => {
        this.router.navigate(['/login']);
      },
      error => {

        return "lul";
      }
    )

  }
}
