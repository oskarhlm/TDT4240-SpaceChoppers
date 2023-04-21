FROM node:14 as base

COPY . /var/spacechoppers
WORKDIR /var/spacechoppers

# Makes sure npm is up to date otherwise install dependencies attempts will fail
# Install dependencies
RUN npm install -g npm && \
    npm install



EXPOSE 6968
CMD ["node", "index.js"]
