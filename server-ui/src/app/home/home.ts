import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {SwaggerCloud} from "./swaggercloudapi";



@Component({
  selector: 'home',

  providers: [SwaggerCloud],

  styleUrls: ['./home.css'],
  templateUrl: './home.html'
})
export class Home {
    date: Date = new Date();

  public repoDetails: Observable<any>;
    public  selected : string;

  constructor(private swaggerCloud:SwaggerCloud){

  }


ngOnInit(){

  this.swaggerCloud.getAllApis()
      .subscribe(repoDetails => {
        this.repoDetails = repoDetails;
          console.log(this.repoDetails);
      });

}


    redirectClick(event){
        console.log(this.selected);
        window.location.href = "http://localhost:8084/swagger-ui/index.html?url="+this.selected;
    }


}
