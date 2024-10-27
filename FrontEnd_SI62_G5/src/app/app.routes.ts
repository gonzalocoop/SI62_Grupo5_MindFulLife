import { Routes } from '@angular/router';
import { CursosComponent } from './components/cursos/cursos.component';
import { CreaeditacursosComponent } from './components/cursos/creaeditacursos/creaeditacursos.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';
import { NosotrosComponent } from './components/landingpage/nosotros/nosotros.component';
import { ServiciosComponent } from './components/landingpage/servicios/servicios.component';
import { PlanesComponent } from './components/landingpage/planes/planes.component';
import { ContactoComponent } from './components/landingpage/contacto/contacto.component';
import { VideoPlayerComponent } from './components/video-player/video-player.component';
import { SuscripcionesComponent } from './components/suscripciones/suscripciones.component';
import { CreaeditasuscripcionesComponent } from './components/suscripciones/creaeditasuscripciones/creaeditasuscripciones.component';
import { RolesComponent } from './components/roles/roles.component';
import { CreaeditarolesComponent } from './components/roles/creaeditaroles/creaeditaroles.component';

export const routes: Routes = [
    {
        path: '', // Ruta vacía para redirigir
        redirectTo: '/landing', // Redirige a la página de inicio
        pathMatch: 'full' // Asegúrate de que coincida con toda la ruta
    },
    {
        path: 'landing', component: LandingpageComponent, // Ruta principal que carga la landing page
        children:[
            {
                path:'nosotros', component:NosotrosComponent
            },
            {
                path:'servicios',component:ServiciosComponent
            },
            {
                path:'planes',component:PlanesComponent
            },
            {
                path:'contacto',component:ContactoComponent
            },
        ]
    },
    {
        path:'cursos',component:CursosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditacursosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditacursosComponent
            }
        ]
    },
    {
        path:'suscripciones',component:SuscripcionesComponent,
        children:[
            {
                path:'nuevo', component:CreaeditasuscripcionesComponent
            },
            {
                path:'ediciones/:id',component:CreaeditasuscripcionesComponent
            }
        ]
    },
    {
        path:'roles',component:RolesComponent,
        children:[
            {
                path:'nuevo', component:CreaeditarolesComponent
            },
            {
                path:'ediciones/:id',component:CreaeditarolesComponent
            }
        ]
    },
    {
        path:'video',component:VideoPlayerComponent,
    }
    
];
