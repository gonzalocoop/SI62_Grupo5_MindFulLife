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
import { VerdescripcionComponent } from './components/cursos/verdescripcion/verdescripcion.component';
import { SesionesComponent } from './components/sesiones/sesiones.component';
import { CreaeditasesionesComponent } from './components/sesiones/creaeditasesiones/creaeditasesiones.component';
import { VideosComponent } from './components/videos/videos.component';
import { CreaeditavideosComponent } from './components/videos/creaeditavideos/creaeditavideos.component';
import { VideosfavoritosComponent } from './components/videosfavoritos/videosfavoritos.component';
import { CreaeditavideosfavoritosComponent } from './components/videosfavoritos/creaeditavideosfavoritos/creaeditavideosfavoritos.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { CreaeditausuariosComponent } from './components/usuarios/creaeditausuarios/creaeditausuarios.component';
import { CursosusuariosComponent } from './components/cursosusuarios/cursosusuarios.component';
import { CreaeditacursosusuariosComponent } from './components/cursosusuarios/creaeditacursosusuarios/creaeditacursosusuarios.component';
import { UsuariossuscripcionesComponent } from './components/usuariossuscripciones/usuariossuscripciones.component';
import { CreaeditausuariossuscripcionesComponent } from './components/usuariossuscripciones/creaeditausuariossuscripciones/creaeditausuariossuscripciones.component';
import { CronogramasComponent } from './components/cronogramas/cronogramas.component';
import { CreaeditacronogramasComponent } from './components/cronogramas/creaeditacronogramas/creaeditacronogramas.component';
import { ComentariosComponent } from './components/comentarios/comentarios.component';
import { CreaeditacomentariosComponent } from './components/comentarios/creaeditacomentarios/creaeditacomentarios.component';
import { VerdescripcionsesComponent } from './components/sesiones/verdescripcionses/verdescripcionses.component';
import { VercomentarioComponent } from './components/comentarios/vercomentario/vercomentario.component';

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
            },
            {
                path:'descripciones/:id',component:VerdescripcionComponent
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
        path:'sesiones',component:SesionesComponent,
        children:[
            {
                path:'nuevo', component:CreaeditasesionesComponent
            },
            {
                path:'ediciones/:id',component:CreaeditasesionesComponent
            },
            {
                path:'descripciones/:id',component:VerdescripcionsesComponent
            }
        ]
    },
    {
        path:'videos',component:VideosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditavideosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditavideosComponent
            }
        ]
    },
    {
        path:'videosfav',component:VideosfavoritosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditavideosfavoritosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditavideosfavoritosComponent
            }
        ]
    },
    {
        path:'usuarios',component:UsuariosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditausuariosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditausuariosComponent
            }
        ]
    },
    {
        path:'cursosusuarios',component:CursosusuariosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditacursosusuariosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditacursosusuariosComponent
            }
        ]
    },
    {
        path:'usuariossuscripciones',component:UsuariossuscripcionesComponent,
        children:[
            {
                path:'nuevo', component:CreaeditausuariossuscripcionesComponent
            },
            {
                path:'ediciones/:id',component:CreaeditausuariossuscripcionesComponent
            }
        ]
    },
    {
        path:'cronogramas',component:CronogramasComponent,
        children:[
            {
                path:'nuevo', component:CreaeditacronogramasComponent
            },
            {
                path:'ediciones/:id',component:CreaeditacronogramasComponent
            }
        ]
    },
    {
        path:'comentarios',component:ComentariosComponent,
        children:[
            {
                path:'nuevo', component:CreaeditacomentariosComponent
            },
            {
                path:'ediciones/:id',component:CreaeditacomentariosComponent
            },
            {
                path:'vercoment/:id',component:VercomentarioComponent
            }
        ]
    },
    {
        path:'video',component:VideoPlayerComponent,
    }
    
];
