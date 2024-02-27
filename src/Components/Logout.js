export default function Logout() {
  <div class="max-w-sm mx-auto mt-10">
    <div class="bg-white shadow-lg rounded-lg p-6">
      <h2 class="text-lg font-semibold text-gray-800">Logout</h2>
      <p class="text-gray-600">Are you sure you want to logout?</p>
      <button
        onclick="handleLogout()"
        class="mt-4 bg-red-500 text-white py-2 px-4 rounded hover:bg-red-700 focus:outline-none focus:shadow-outline"
      >
        Logout
      </button>
    </div>
  </div>;
}
