import {Component, OnInit} from '@angular/core';
import {TownStatistics} from "../../dto/town-statistics";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {

  public towns: TownStatistics[];

  constructor() {}

  ngOnInit(): void {
    // TODO
    this.towns = [
      {
        name: "New Quarkum",
        population: 12,
        golems: 5,
        mayor: "Quarkian"
      },
      {
        name: "Odeßos",
        population: 4,
        golems: 0,
        mayor: "Gory26"
      },
      {
        name: "Memphis",
        population: 6,
        golems: 2,
        mayor: "Shushumiga"
      }
    ];
  }

}
