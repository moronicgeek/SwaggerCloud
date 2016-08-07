import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class Swagger {
    constructor(private http: Http) {}


    getSwaggerRepoorOrg() {
        return this.makeRequest(`test/api/all`);
    }

    private makeRequest(path: string) {

        let url = `http://localhost:8084/${ path }`;
        return this.http.get(url)
            .map((res) => res.json());
    }
}
