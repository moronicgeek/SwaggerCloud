import {Component} from '@angular/core';

@Component({
  selector: 'home',
  pipes: [],
  providers: [],
  directives: [],
  styleUrls: ['./home.css'],
  templateUrl: './home.html'
})
export class Home {

   private urls: string[] = ["http://localhost:8081/swagger.json", "http://localhost:8081/swagger.json", "http://localhost:8081/swagger.json"];


  public onClick(event){
    alert("i am clicked");
    window.location.href = "http://localhost:3000/swagger-ui/index.html";
  }

  public makeRestCall(){

  }
}
