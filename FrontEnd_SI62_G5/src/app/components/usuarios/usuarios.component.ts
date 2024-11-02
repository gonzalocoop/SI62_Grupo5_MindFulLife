import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { ListarusuariosComponent } from './listarusuarios/listarusuarios.component';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    RouterOutlet,
    ListarusuariosComponent,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    RouterModule,
  ],
  templateUrl: './usuarios.component.html',
  styleUrl: './usuarios.component.css'
})
export class UsuariosComponent {
  constructor(public route: ActivatedRoute) {}

}
