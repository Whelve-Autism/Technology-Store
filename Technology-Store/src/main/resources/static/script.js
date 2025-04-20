document.addEventListener('DOMContentLoaded', () => {
    const apiUrl = 'http://localhost:8080/technology';
    let technologies = [];

    /*
      监听设备类型选择，根据选择更新表单字段。
      Monitoring device type selection and updating form fields according to the selection.
     */
    document.getElementById('type').addEventListener('change', () => {
        const type = document.getElementById('type').value;
        const formFields = document.getElementById('form-fields');
        formFields.innerHTML = '';
        switch (type) {
            case 'Laptop':
                formFields.innerHTML = `
                    <label for="id">ID:</label><input type="number" id="id" placeholder="ID" required>
                    <label for="name">Name:</label><input type="text" id="name" placeholder="Name" required>
                    <label for="manufacturerName">Manufacturer Name:</label><input type="text" id="manufacturerName" placeholder="Manufacturer Name" required>
                    <label for="location">Location:</label><input type="text" id="location" placeholder="Location" required>
                    <label for="displaySize">Display Size:</label><input type="text" id="displaySize" placeholder="Display Size" required>
                    <label for="processor">Processor:</label><input type="text" id="processor" placeholder="Processor" required>
                `;
                break;
            case 'Tablet':
                formFields.innerHTML = `
                    <label for="id">ID:</label><input type="number" id="id" placeholder="ID" required>
                    <label for="name">Name:</label><input type="text" id="name" placeholder="Name" required>
                    <label for="manufacturerName">Manufacturer Name:</label><input type="text" id="manufacturerName" placeholder="Manufacturer Name" required>
                    <label for="location">Location:</label><input type="text" id="location" placeholder="Location" required>
                    <label for="displaySize">Display Size:</label><input type="text" id="displaySize" placeholder="Display Size" required>
                    <label for="operationSystem">Operating System:</label><input type="text" id="operationSystem" placeholder="Operating System" required>
                `;
                break;
            case 'SmartBand':
                formFields.innerHTML = `
                    <label for="id">ID:</label><input type="number" id="id" placeholder="ID" required>
                    <label for="name">Name:</label><input type="text" id="name" placeholder="Name" required>
                    <label for="manufacturerName">Manufacturer Name:</label><input type="text" id="manufacturerName" placeholder="Manufacturer Name" required>
                    <label for="location">Location:</label><input type="text" id="location" placeholder="Location" required>
                    <label for="measurement">Measurement:</label><input type="text" id="measurement" placeholder="Measurement" required>
                    <label for="batteryCapacity">Battery Capacity:</label><input type="number" id="batteryCapacity" placeholder="Battery Capacity" required>
                `;
                break;
            case 'SmartWatch':
                formFields.innerHTML = `
                    <label for="id">ID:</label><input type="number" id="id" placeholder="ID" required>
                    <label for="name">Name:</label><input type="text" id="name" placeholder="Name" required>
                    <label for="manufacturerName">Manufacturer Name:</label><input type="text" id="manufacturerName" placeholder="Manufacturer Name" required>
                    <label for="location">Location:</label><input type="text" id="location" placeholder="Location" required>
                    <label for="measurement">Measurement:</label><input type="text" id="measurement" placeholder="Measurement" required>
                    <label for="connectionType">Connection Type:</label><input type="text" id="connectionType" placeholder="Connection Type" required>
                `;
                break;
            default:
                formFields.innerHTML = '';
        }
    });

    /*
      监听添加表单提交，发送 POST 请求到服务器。
      Monitor form submission of adding and send a POST request to the server.
     */
    document.getElementById('add-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        const type = document.getElementById('type').value;
        const id = parseInt(document.getElementById('id').value, 10);
        const name = document.getElementById('name').value;
        const manufacturerName = document.getElementById('manufacturerName').value;
        const location = document.getElementById('location').value;
        let data = {
            id,
            name,
            type,
            manufacturer: { name: manufacturerName, location }
        };

        /*
          检查 ID 是否已经存在，如果存在则显示错误消息。
          Check if the ID already exists, if it does, display an error message.
         */
        if (technologies.some(tech => tech.id === id)) {
            document.getElementById('add-message').textContent = 'ID already exists. Please enter a unique ID.';
            document.getElementById('add-message').className = 'animate__animated animate__fadeIn';
            return;
        }

        switch (type) {
            case 'Laptop':
                data.displaySize = document.getElementById('displaySize').value;
                data.processor = document.getElementById('processor').value;
                break;
            case 'Tablet':
                data.displaySize = document.getElementById('displaySize').value;
                data.operationSystem = document.getElementById('operationSystem').value;
                break;
            case 'SmartBand':
                data.measurement = document.getElementById('measurement').value;
                data.batteryCapacity = parseInt(document.getElementById('batteryCapacity').value, 10);
                break;
            case 'SmartWatch':
                data.measurement = document.getElementById('measurement').value;
                data.connectionType = document.getElementById('connectionType').value;
                break;
        }

        /*
          打印要发送的数据，用于调试。
          Print the data to be sent for debugging purposes.
         */
        console.log('Adding Technology:', data);

        try {
            const response = await fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                document.getElementById('add-message').textContent = 'Technology added successfully';
                document.getElementById('add-message').className = 'success animate__animated animate__fadeIn';
                await loadTechnologyList();
            } else {
                document.getElementById('add-message').textContent = `Failed to add technology: ${response.statusText}`;
                document.getElementById('add-message').className = 'animate__animated animate__fadeIn';
            }
        } catch (error) {
            console.error('Error adding technology:', error);
            document.getElementById('add-message').textContent = `An error occurred while adding technology: ${error.message}`;
            document.getElementById('add-message').className = 'animate__animated animate__fadeIn';
        }
    });

    /*
      加载科技设备列表，并渲染到页面。
      Load the list of technology devices and render it to the page.
     */
    async function loadTechnologyList() {
        try {
            const response = await fetch(apiUrl);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            technologies = await response.json();
            console.log('Technologies loaded:', technologies);
            renderTechnologyList(technologies);
            updateRefreshTime();
        } catch (error) {
            console.error('Error loading technology list:', error);
            document.getElementById('add-message').textContent = 'Failed to load technology list';
            document.getElementById('add-message').className = 'animate__animated animate__fadeIn';
        }
    }

    /*
      渲染科技设备列表到页面。
      Render the list of technology devices to the page.
     */
    function renderTechnologyList(technologies) {
        const tableBody = document.getElementById('technology-table');
        tableBody.innerHTML = '';
        technologies.forEach(tech => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${tech.id}</td>
                <td>${tech.name}</td>
                <td>${tech.manufacturer.name}</td>
                <td>${tech.manufacturer.location}</td>
                <td>
                    <button onclick="viewTechnology(${tech.id})">View</button>
                    <button onclick="updateTechnology(${tech.id})">Update</button>
                    <button onclick="deleteTechnology(${tech.id})">Delete</button>
                </td>
            `;
            tableBody.appendChild(row);
        });
        console.log('Rendered Technologies:', technologies);
    }

    /*
      更新刷新时间。
      Update the refresh time.
     */
    function updateRefreshTime() {
        const now = new Date();
        const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' };
        const formattedTime = now.toLocaleDateString('en-US', options);
        document.getElementById('refresh-time').textContent = `Last Refreshed: ${formattedTime}`;
    }

    /*
      显示科技设备的详细信息。
      Show the details of a technology device.
     */
    window.viewTechnology = async (id) => {
        const response = await fetch(`${apiUrl}/${id}`);
        const technology = await response.json();
        document.getElementById('detail-id').textContent = technology.id;
        document.getElementById('detail-name').textContent = technology.name;
        document.getElementById('detail-manufacturer').textContent = technology.manufacturer.name;
        document.getElementById('detail-location').textContent = technology.manufacturer.location;
        const detailFields = document.getElementById('detail-fields');
        detailFields.innerHTML = '';

        if (technology.displaySize) {
            detailFields.innerHTML += `<p>Display Size: ${technology.displaySize}</p>`;
        }
        if (technology.processor) {
            detailFields.innerHTML += `<p>Processor: ${technology.processor}</p>`;
        }
        if (technology.operationSystem) {
            detailFields.innerHTML += `<p>Operating System: ${technology.operationSystem}</p>`;
        }
        if (technology.measurement) {
            detailFields.innerHTML += `<p>Measurement: ${technology.measurement}</p>`;
        }
        if (technology.batteryCapacity) {
            detailFields.innerHTML += `<p>Battery Capacity: ${technology.batteryCapacity}</p>`;
        }
        if (technology.connectionType) {
            detailFields.innerHTML += `<p>Connection Type: ${technology.connectionType}</p>`;
        }

        document.getElementById('technology-list').style.display = 'none';
        document.getElementById('technology-detail').style.display = 'block';
    };

    /*
      返回到列表。
      Back to list.
     */
    document.getElementById('back-to-list').addEventListener('click', () => {
        document.getElementById('technology-list').style.display = 'block';
        document.getElementById('technology-detail').style.display = 'none';
    });

    /*
      更新科技设备。
      Update a technology device.
     */
    window.updateTechnology = async (id) => {
        const type = prompt('Enter type (Laptop, Tablet, SmartBand, SmartWatch):');
        const name = prompt('Enter new name:');
        const manufacturerName = prompt('Enter new manufacturer name:');
        const location = prompt('Enter new location:');
        let data = {
            id,
            name,
            type,
            manufacturer: { name: manufacturerName, location }
        };

        switch (type) {
            case 'Laptop':
                const displaySize = prompt('Enter new display size:');
                const processor = prompt('Enter new processor:');
                data.displaySize = displaySize;
                data.processor = processor;
                break;
            case 'Tablet':
                const displaySizeTablet = prompt('Enter new display size:');
                const operationSystem = prompt('Enter new operation system:');
                data.displaySize = displaySizeTablet;
                data.operationSystem = operationSystem;
                break;
            case 'SmartBand':
                const measurement = prompt('Enter new measurement:');
                const batteryCapacity = parseInt(prompt('Enter new battery capacity:'), 10);
                data.measurement = measurement;
                data.batteryCapacity = batteryCapacity;
                break;
            case 'SmartWatch':
                const measurementWatch = prompt('Enter new measurement:');
                const connectionType = prompt('Enter new connection type:');
                data.measurement = measurementWatch;
                data.connectionType = connectionType;
                break;
        }

        console.log('Updating Technology:', data);

        const response = await fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        const message = response.ok ? 'Technology updated successfully' : 'Failed to update technology';
        alert(message);
        await loadTechnologyList();
    };

    /*
      删除科技设备。
      Delete a technology device.
     */
    window.deleteTechnology = async (id) => {
        if (confirm('Are you sure you want to delete this technology?')) {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: 'DELETE'
            });
            const message = response.ok ? 'Technology deleted successfully' : 'Failed to delete technology';
            alert(message);
            await loadTechnologyList();
        }
    };

    /*
      刷新科技设备列表。
      Refresh the technology list.
     */
    document.getElementById('refresh-button').addEventListener('click', () => {
        loadTechnologyList().then(() => console.log('Technology list refreshed'));
    });

    /*
      搜索科技设备。
      Search for technology devices.
     */
    document.getElementById('search-button').addEventListener('click', () => {
        const query = document.getElementById('search').value.toLowerCase();
        const filteredTechnologies = technologies.filter(tech => {

            /*
              添加日志记录以检查tech对象。
              Logging added to check the tech object.
             */
            console.log('Tech object:', tech);

            /*
              添加日志记录以检查tech对象的各个属性，不区分大小写。
              Logging added to check each property of the tech object, ignoring case.
             */
            const techType = tech.type ? tech.type.toLowerCase() : '';
            const techName = tech.name ? tech.name.toLowerCase() : '';
            const techManufacturerName = tech.manufacturer && tech.manufacturer.name ? tech.manufacturer.name.toLowerCase() : '';

            return techType.includes(query) || techName.includes(query) || techManufacturerName.includes(query);
        });
        console.log('Filtered Technologies:', filteredTechnologies); // 调试信息
        renderTechnologyList(filteredTechnologies);
    });

    /*
      初始化界面，加载科技设备列表。
      Initialize the interface, load the technology list.
     */
    loadTechnologyList().then(() => console.log('Technology list loaded'));
});