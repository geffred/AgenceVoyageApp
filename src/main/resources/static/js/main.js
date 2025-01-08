const deleteButtons = document.querySelectorAll(".btn-danger");

deleteButtons.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    if (!window.confirm("Voulez-vous vraiment effectuer cette suppression?")) {
      e.preventDefault();
    }
  });
});
