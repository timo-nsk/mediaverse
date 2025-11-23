import {Component, inject, OnInit} from '@angular/core';
import {MediumApiService} from '../service/api.service';
import { CommonModule } from '@angular/common';
import {RandomMediumService} from './random-medium.service';
import {StatistikComponent} from './statistik-component/statistik-component';
import {MediumTypPipe} from '../mediumtyp.pipe';
import {PlatformPipe} from '../platform.pipe';
import {Medium} from '../domain/medium';

@Component({
  selector: 'app-home-component',
  imports: [CommonModule, StatistikComponent, MediumTypPipe, PlatformPipe],
  templateUrl: './home-component.html',
  standalone: true,
  styleUrls: ['./home-component.css', '../../styles.css']
})
export class HomeComponent implements OnInit {
  apiService : MediumApiService = inject(MediumApiService)
  randomMediumService! : RandomMediumService;
  currentMedien : { [key: string]: any[] } = {}
  randomMedium : any = null;

  ngOnInit(): void {
    this.apiService.getCurrentMedien().subscribe({
      next: data => {
        this.currentMedien = data;
        this.randomMediumService = new RandomMediumService(this.currentMedien)
        console.log(this.currentMedien)
      }
    })
  }

  wuerfelCurrentMedien() {
    this.randomMedium = this.randomMediumService.getRandomMedium();
  }

  protected readonly Object = Object;
}
