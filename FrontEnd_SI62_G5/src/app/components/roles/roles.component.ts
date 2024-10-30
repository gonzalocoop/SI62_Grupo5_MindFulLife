import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { ListarrolesComponent } from './listarroles/listarroles.component';

@Component({
  selector: 'app-roles',
  standalone: true,
  imports: [RouterOutlet,ListarrolesComponent,MatToolbarModule, MatIconModule, MatMenuModule, MatButtonModule, RouterModule],
  templateUrl: './roles.component.html',
  styleUrl: './roles.component.css'
})
export class RolesComponent {
  constructor(public route:ActivatedRoute){}
}
