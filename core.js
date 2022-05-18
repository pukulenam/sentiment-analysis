const Hapi = require('@hapi/hapi');
const routes = require('./routes');

const ito = async () => {
  const serverhttp = Hapi.server({
    port: 5000,
    host: 'localhost',
    routes: {
      cors: {
        origin: ['*'],
      },
    },
  });

  serverhttp.route(routes);

  await serverhttp.start();
  console.log(`Server sedang berjalan ${serverhttp.info.uri}`);
};

ito();
