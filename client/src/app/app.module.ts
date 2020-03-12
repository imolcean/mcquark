import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { NewsComponent } from './news/news.component';
import { SharedModule } from './shared/shared.module';
import { RouterModule, Routes } from "@angular/router";
import { ProjectComponent } from './project/project.component';
import { ContactComponent } from './contact/contact.component';
import {EditorComponent} from "./admin/editor/editor.component";
import {DashboardComponent} from "./admin/dashboard/dashboard.component";
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
import { UserEditorComponent } from './registration/user-editor.component';
import {CheckboxModule} from "primeng/checkbox";

const appRoutes: Routes = [
  { path: '', component: NewsComponent },
  { path: 'project', component: ProjectComponent },
  // { path: 'contact', component: ContactComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'register', component: UserEditorComponent },
  { path: 'admin', component: DashboardComponent },
  { path: 'admin/editor', component: EditorComponent },
  { path: '**', component: NewsComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NewsComponent,
    ProjectComponent,
    ContactComponent,
    DashboardComponent,
    EditorComponent,
    ProfileComponent,
    UserEditorComponent
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
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
