class CustomNavigationBar extends HTMLElement {
  constructor() {
    super();
    const shadowRoot = this.attachShadow({ mode: 'open' });
    shadowRoot.innerHTML = `
        <style>   
        :host {
          display: flex;
          position: fixed;
          bottom: 0;
          flex-direction: row;
          flex-wrap: wrap;
          justify-content: space-between;
          background-color: #ccc;
          border-radius: 0.5em;
          margin-bottom: 0.5em;
          width: 100vw;

        }

        ::slotted(a){
        }
        </style>
        <div class="navibar">
          <slot></slot>
        </div>`;
  }

};

customElements.define('navigation-bar', CustomNavigationBar);