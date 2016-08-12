import { Injectable } from '@angular/core';
import {Http, URLSearchParams, Response} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class SwaggerCloud {
    constructor(private http: Http) {}



    getAllApis() {
        return this.makeRequest(`all/`);
    }

    private makeRequest(path: string) {


        let url = `http://localhost:8084/swaggercloud/${ path }`;
        return this.http.get(url)
            .map((res) => res.json());
    }
}
