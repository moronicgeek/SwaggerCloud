import {Component} from '@angular/core';
import {Swagger} from '../github/shared/swagger';

@Component({
  selector: 'home',
  pipes: [],
  providers: [Swagger],
  directives: [],
  styleUrls: ['./home.css'],
  templateUrl: './home.html'
})
export class Home {

  private urls: string[] = ["http://localhost:8081/swagger.json", "http://localhost:8081/swagger.json", "http://localhost:8081/swagger.json"];

  private swaggerApis : any = [];


  constructor(public swagger:Swagger){

  }

  ngOnInit(){
    this.swagger.getSwaggerRepoorOrg()
        .subscribe(swaggerApis => {
          this.swaggerApis = swaggerApis;
        });
  }

  public onClick(event){

   window.location.href = "swagger-ui"
  }


}
