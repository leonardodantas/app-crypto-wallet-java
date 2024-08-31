print("INSERINDO DADOS");

db = connect("mongodb://leonardo:123456@mongo:27017/cryptowallet?authSource=admin");

const documentos = [
    { name: "ACMFT", description: "AC Milan" },
    { name: "ADA", description: "Cardano" },
    { name: "ALCX", description: "Alchemix" },
    { name: "ALGO", description: "Algorand" },
    { name: "ALICE", description: "MyNeighborAlice" },
    { name: "ALLFT", description: "Alliance" },
    { name: "ANKR", description: "ANKR" },
    { name: "ARGFT", description: "Argentine Football Association" },
    { name: "ASRFT", description: "AS Roma" },
    { name: "AXS", description: "Axie Infinity" },
    { name: "BARFT", description: "FC Barcelona" },
    { name: "BCH", description: "Bitcoin Cash" },
    { name: "BNT", description: "BANCOR" },
    { name: "BTC", description: "Bitcoin" },
    { name: "CITYFT", description: "Manchester City FC" },
    { name: "COMP", description: "Compound" },
    { name: "DOGE", description: "Dogecoin" },
    { name: "DOT", description: "Polkadot" },
    { name: "DYDX", description: "dYdX" },
    { name: "ENS", description: "Ethereum Name Service" },
    { name: "ETH", description: "Ethereum" },
    { name: "INTERFT", description: "Inter Milan" },
    { name: "JUVFT", description: "Juventus" },
    { name: "LPT", description: "Livepeer" },
    { name: "LRC", description: "Loopring" },
    { name: "MBPRK05", description: "Fluxo de Pagamentos 5" },
    { name: "MBPRK06", description: "Precatorio MB BR06" },
    { name: "MBSANTOS01", description: "Token da Vila" },
    { name: "MBVASCO01", description: "Vasco Token" },
    { name: "MCO2", description: "Moss Carbon Credit" },
    { name: "MENGOFT", description: "Flamengo" },
    { name: "MKR", description: "Maker" },
    { name: "NAVIFT", description: "Natus Vincere" },
    { name: "OGFT", description: "OG eSports" },
    { name: "OMG", description: "Omg Network" },
    { name: "PAXG", description: "PAX Gold" },
    { name: "PFLFT", description: "Professional Fighters League" },
    { name: "PORFT", description: "Portugal National Team FT" },
    { name: "PSGFT", description: "Paris Saint-Germain" },
    { name: "QNT", description: "Quant" },
    { name: "RACA", description: "Radio Caca" },
    { name: "RAD", description: "Radicle" },
    { name: "REN", description: "Ren" },
    { name: "REQ", description: "Request" },
    { name: "SAND", description: "The Sandbox" },
    { name: "SAUBERFT", description: "Alfa Romeo Racing ORLEN" },
    { name: "SCCPFT", description: "Corinthians" },
    { name: "SHIB", description: "Shiba Inu" },
    { name: "SLP", description: "Smooth Love Potion" },
    { name: "SOL", description: "Solana" }
];

documentos.forEach(doc => {
    db.digital_currency_acronym.updateOne(
        { name: doc.name },
        { $set: doc },
        { upsert: true }
    );
});

print("DADOS INSERIDOS COM SUCESSO");