import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'roles'
})
export class RolesPipe implements PipeTransform {

  transform(roles: string[], ...args: unknown[]): string {
    let str: string = '';

    for (const role of roles) {
      str += role.charAt(0);
    }

    return str.toUpperCase();
  }

}
