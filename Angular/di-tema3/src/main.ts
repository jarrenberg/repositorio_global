import './style.css'
import typescriptLogo from './typescript.svg'

document.querySelector<HTMLDivElement>('#app')!.innerHTML = `
  
  <div>
    <a href="https://iesplayamar.es/" target="_blank">
      <img src="https://iesplayamar.es/wp-content/uploads/2021/09/logo-ies-playamar.png" class="logo" alt="playamar-logo" />
    </a>
    <a href="https://www.typescriptlang.org/" target="_blank">
      <img src="${typescriptLogo}" class="logo vanilla" alt="TypeScript logo" />
    </a>
    <h1>Tarea 1 del Tema 3 de DI</h1>
    <p class="read-the-docs">
      Diseñado y realizado por el alumno Jorge Arrenberg del modulo de 2DAM del IES Playamar
       &#128512;
    </p>
  </div>
`
