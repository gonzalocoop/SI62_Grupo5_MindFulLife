import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarcronogramasComponent } from './listarcronogramas/listarcronogramas.component';

@Component({
  selector: 'app-cronogramas',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarcronogramasComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
  ],
  templateUrl: './cronogramas.component.html',
  styleUrl: './cronogramas.component.css'
})
export class CronogramasComponent {
  constructor(public route: ActivatedRoute) {}

}
