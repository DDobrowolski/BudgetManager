import { Component, OnInit } from '@angular/core';
import { AccserviceService } from '../service/accservice.service';
import { Router } from '../../../node_modules/@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  errorMessage: string;
  constructor(public accountService:AccserviceService, public router: Router) { }

  ngOnInit() {
  }

  register(){
    this.accountService.createAccount(this.user).subscribe(data =>{
      this.router.navigate(['/login']);
    }, err => {
      console.log(err);
      this.errorMessage = "username already exists";
    })
  }


}
