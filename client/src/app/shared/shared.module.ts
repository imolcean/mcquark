import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { MenuComponent } from './menu/menu.component';
import { RouterModule } from "@angular/router";
import { IconComponent } from './icon/icon.component';
import { AngularSvgIconModule } from "angular-svg-icon";
import { HttpClientModule } from "@angular/common/http";
import { StatusComponent } from './status/status.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { ProfileCardComponent } from './profile-card/profile-card.component';

@NgModule({
  declarations: [
    CardComponent,
    MenuComponent,
    IconComponent,
    StatusComponent,
    StatisticsComponent,
    ProfileCardComponent
  ],
  exports: [
    CardComponent,
    MenuComponent,
    IconComponent,
    StatusComponent,
    StatisticsComponent,
    ProfileCardComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    AngularSvgIconModule
  ]
})
export class SharedModule { }
