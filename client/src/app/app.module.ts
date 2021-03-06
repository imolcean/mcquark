import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { NewsComponent } from './news/news.component';
import { SharedModule } from './shared/shared.module';
import { RouterModule, Routes } from "@angular/router";
import { ProjectComponent } from './project/project.component';
import { ContactComponent } from './contact/contact.component';
import {PostEditorComponent} from "./internal/post-editor/post-editor.component";
import {DashboardComponent} from "./internal/dashboard/dashboard.component";
import {EditorModule} from "primeng/editor";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {TableModule} from 'primeng/table';
import {ButtonModule} from "primeng/button";
import {ToolbarModule} from "primeng/toolbar";
import {TabViewModule} from "primeng/tabview";
import { ProfileComponent } from './profile/profile.component';
import {FieldsetModule} from "primeng/fieldset";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {XhrInterceptor} from "./xhr-interceptor";
import { UserEditorComponent } from './user-editor/user-editor.component';
import {CheckboxModule} from "primeng/checkbox";
import {AuthenticationGuard} from "./shared/authentication/authentication.guard";
import {AuthorizationGuard} from "./shared/authorization/authorization.guard";
import {AuthenticationService} from "./shared/authentication/authentication.service";
import {EmailEditorComponent} from "./profile/email-editor/email-editor.component";
import { PasswordEditorComponent } from './profile/password-editor/password-editor.component';
import {ProfileCardComponent} from "./profile/profile-card/profile-card.component";
import { PostComponent } from './news/post/post.component';

export function authInitFactory(auth: AuthenticationService) {
  console.log("Initializaer called");
  return () => new Promise((resolve: any, _reject: any) => {
    auth.loadCurrentUser()
      .subscribe(_response => {
        console.log("Auth info loaded");
        resolve(true);
      });
  });
}

const appRoutes: Routes = [
  {
    path: '',
    component: NewsComponent
  },
  {
    path: 'news',
    component: NewsComponent
  },
  {
    path: 'news/:postId',
    component: PostComponent
  },
  {
    path: 'project',
    component: ProjectComponent
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [ AuthenticationGuard ],
    data: { authenticated: true }
  },
  // TODO: Profiles of other users visible
  // {
  //   path: 'profile/:userId',
  //   component: ProfileComponent,
  //   canActivate: [ AuthenticationGuard ],
  //   data: { authenticated: true }
  // },
  {
    path: 'profile/:userId/edit',
    component: UserEditorComponent,
    canActivate: [ AuthorizationGuard ],
    data: { roles: ['ADMIN'] }
  },
  {
    path: 'profile/edit/email',
    component: EmailEditorComponent,
    canActivate: [ AuthenticationGuard ],
    data: { authenticated: true }
  },
  {
    path: 'profile/edit/password',
    component: PasswordEditorComponent,
    canActivate: [ AuthenticationGuard ],
    data: { authenticated: true }
  },
  {
    path: 'register',
    component: UserEditorComponent,
    canActivate: [ AuthenticationGuard ],
    data: { authenticated: false }
  },
  {
    path: 'internal',
    component: DashboardComponent,
    canActivate: [ AuthorizationGuard ],
    data: { roles: ['ADMIN', 'EDITOR'] }
  },
  {
    path: 'internal/editor/:postId',
    component: PostEditorComponent,
    canActivate: [ AuthorizationGuard ],
    data: { roles: ['ADMIN', 'EDITOR'] }
  },
  {
    path: 'internal/editor',
    component: PostEditorComponent,
    canActivate: [ AuthorizationGuard ],
    data: { roles: ['ADMIN', 'EDITOR'] }
  },
  {
    path: '**',
    component: NewsComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    NewsComponent,
    ProjectComponent,
    ContactComponent,
    DashboardComponent,
    PostEditorComponent,
    ProfileComponent,
    ProfileCardComponent,
    UserEditorComponent,
    EmailEditorComponent,
    PasswordEditorComponent,
    PostComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    NoopAnimationsModule,
    FormsModule,
    SharedModule,
    EditorModule,
    InputTextModule,
    TableModule,
    ButtonModule,
    ToolbarModule,
    TabViewModule,
    FieldsetModule,
    CheckboxModule
  ],
  providers: [
    { provide: APP_INITIALIZER, useFactory: authInitFactory, deps: [AuthenticationService], multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
