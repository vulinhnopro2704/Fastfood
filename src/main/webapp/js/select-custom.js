document.querySelectorAll(".dropdown-item").forEach((item) => {
  item.addEventListener("click", () => {
    // Update the selected option
    const selected = document.querySelector(".selected-option");
    selected.textContent = item.getAttribute("data-value");

    // Mark this item as active
    document.querySelectorAll(".dropdown-item").forEach((i) => {
      i.classList.remove("active");
    });
    item.classList.add("active");

    // Close the dropdown menu
    const menu = document.querySelector(".dropdown-menu");
    menu.style.visibility = "hidden";
    menu.style.opacity = 0;
  });
});

// Optional: Close dropdown when clicked outside
document.addEventListener("click", (event) => {
  const dropdown = document.querySelector(".dropdown");
  if (!dropdown.contains(event.target)) {
    const menu = document.querySelector(".dropdown-menu");
    menu.style.visibility = "hidden";
    menu.style.opacity = 0;
  }
});

// Toggle dropdown visibility
document.querySelector(".dropdown-toggle").addEventListener("click", (e) => {
  const menu = document.querySelector(".dropdown-menu");
  const isVisible = menu.style.visibility === "visible";
  menu.style.visibility = isVisible ? "hidden" : "visible";
  menu.style.opacity = isVisible ? 0 : 1;
});

function handlClick(){
  console.log("type : ");
}