import {Component} from '@angular/core';
import {Observable} from "rxjs";
import {SwaggerCloud} from "./swaggercloudapi";
import {AlertComponent, DATEPICKER_DIRECTIVES, BUTTON_DIRECTIVES} from "ng2-bootstrap";
import {ApiFilter} from "./apipipe";



@Component({
  selector: 'home',
  pipes: [ApiFilter],
  providers: [SwaggerCloud],
  directives: [BUTTON_DIRECTIVES,AlertComponent, DATEPICKER_DIRECTIVES],
  styleUrls: ['./home.css'],
  templateUrl: './home.html'
})
export class Home {
    date: Date = new Date();

  public repoDetails: Observable<any>;

  constructor(private swaggerCloud:SwaggerCloud){

  }


ngOnInit(){

  this.swaggerCloud.getRepoForOrg()
      .subscribe(repoDetails => {
        this.repoDetails = repoDetails;
      });
}

  onClick (){
    console.log("It's clicked");

    this.swaggerCloud.getRepoForOrg()
        .subscribe(repoDetails => {
          this.repoDetails = repoDetails;
        });

    console.log(this.repoDetails);
  }

}
