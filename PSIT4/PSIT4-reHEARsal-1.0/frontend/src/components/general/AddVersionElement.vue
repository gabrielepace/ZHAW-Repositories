<template>
  <div class="addVersionElement">
    <span class="round-plus-sign">
      <button class="round-button bg-primary">
        <svg
          class="bi bi-plus"
          width="1.5em"
          height="1.5em"
          viewBox="0 0 16 16"
          fill="currentColor"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M8 3.5a.5.5 0 01.5.5v4a.5.5 0 01-.5.5H4a.5.5 0 010-1h3.5V4a.5.5 0 01.5-.5z"
            clip-rule="evenodd"
          />
          <path
            fill-rule="evenodd"
            d="M7.5 8a.5.5 0 01.5-.5h4a.5.5 0 010 1H8.5V12a.5.5 0 01-1 0V8z"
            clip-rule="evenodd"
          />
        </svg>
      </button>
    </span>
    <label>Add new Version</label>
  </div>
</template>

<style scoped>
.round-plus-sign {
  padding-right: 1%;
}
.round-button {
  border: none;
  color: white;
  border-radius: 50%;
  width: 40px;
  height: 40px;
}

.addVersionElement {
  padding: 2% 2%;
}
</style>
