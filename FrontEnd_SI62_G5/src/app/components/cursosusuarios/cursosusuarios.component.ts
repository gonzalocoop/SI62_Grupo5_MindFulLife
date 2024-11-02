import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarcursosusuariosComponent } from './listarcursosusuarios/listarcursosusuarios.component';

@Component({
  selector: 'app-cursosusuarios',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarcursosusuariosComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
  ],
  templateUrl: './cursosusuarios.component.html',
  styleUrl: './cursosusuarios.component.css'
})
export class CursosusuariosComponent {
  constructor(public route: ActivatedRoute) {}

}
