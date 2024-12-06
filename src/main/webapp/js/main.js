function callController(path, data, callback, method = 'GET') {
    const baseUrl = '/final_exam/controller/controller.php?path=';
    const url = method.toUpperCase() === 'GET' ? `${baseUrl + encodeURIComponent(path)}&${data}` : baseUrl + encodeURIComponent(path);
    console.log('Request URL:', url);
    console.log('Request Method:', method.toUpperCase());
    console.log('Request Data:', data);

    fetch(url, {
        method: method.toUpperCase(),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: method.toUpperCase() === 'POST' ? data : undefined
    }).then(response => {
        console.log("Response Status:", response.status);
        console.log("Response Headers:", response.headers);
        if (response.ok) {
            alert('Request resolved successfully');
            if (method.toUpperCase() === 'GET') {
                window.location.href = url;
            }
            callback();
        } else {
            alert('Failed to resolve request');
        }
        return response.text(); // Get the response text for debugging
    }).then(text => {
    }).catch(error => {
        console.error('Error:', error);
    });
}