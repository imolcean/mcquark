import {Directive, Input, TemplateRef, ViewContainerRef} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {UserDto} from "../../dto/dto";

@Directive({
  selector: '[rbacShow]'
})
export class RbacShowDirective {

  private allowedRoles: string[];

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authService: AuthenticationService) {
    this.allowedRoles = [];
  }

  @Input()
  public set rbacShow(allowedRoles: string[]) {
    this.allowedRoles = allowedRoles.map(role => role.toUpperCase());

    const user: UserDto | undefined = this.authService.currentUser;

    if (!this.allowedRoles || this.allowedRoles.length === 0 || !user) {
      this.viewContainer.clear();
      return;
    }

    const allowed: boolean = user.roles.filter(role => this.allowedRoles.includes(role)).length > 0;

    if (allowed) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    }
    else {
      this.viewContainer.clear();
    }
  }
}
