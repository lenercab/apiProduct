import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //https://www.youtube.com/watch?v=-FZHBTqBGBM
  username: string
  password: string
  message: any

  constructor(private restapi: RestapiService) { }

  ngOnInit() {
   
  }

  doLogin() { 
    console.log("entro....")
    let body = {}
    let response = this.restapi.createInventory(this.username, this.password, body)
    response.subscribe(data => {
      console.log(data)
    })
  }

}
