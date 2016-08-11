import {Injectable, Pipe, PipeTransform} from "@angular/core";
/**
 * Created by muhammedpatel on 2016/08/11.
 */
@Pipe({
    name: 'apiFilter',
    pure: false
})
@Injectable()
export class ApiFilter implements PipeTransform {
    transform(items: any[], args: any[]): any {
        // filter items array, items which match and return true will be kept, false will be filtered out
        return items.filter(item => item.title.indexOf(args[0].title) !== -1);
    }
}