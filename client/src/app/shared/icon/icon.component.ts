import {ChangeDetectionStrategy, Component, Input} from '@angular/core';

@Component({
  selector: 'app-icon',
  template: '<svg-icon *ngIf="path" [src]="path" [svgStyle]="{ \'width.px\':25 }"></svg-icon>',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class IconComponent {

  public path: string | undefined;

  @Input()
  set name(iconName: string) {
    this.path = "assets/icons/" + iconName + ".svg";
  }

  constructor() {}

}
