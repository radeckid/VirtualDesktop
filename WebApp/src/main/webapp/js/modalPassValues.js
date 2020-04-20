$('#editModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var name = button.data('name'); // Extract info from data-* attributes
    var surname = button.data('surname'); // Extract info from data-* attributes
    var email = button.data('email');

    var modal = $(this);
    modal.find('#user-name').val(name);
    modal.find('#user-surname').val(surname);
    modal.find('#user-email').val(email);
    modal.find('#user-oldEmail').val(email)
});

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var name = button.data('name'); // Extract info from data-* attributes
    var surname = button.data('surname'); // Extract info from data-* attributes
    var id = button.data('id');

    document.getElementById('delete-name').innerHTML = name;
    document.getElementById('delete-surname').innerHTML = surname;
    var form = document.getElementById('deleteForm');
    form.action = 'deleteUserById.jsp?id=' + id
});