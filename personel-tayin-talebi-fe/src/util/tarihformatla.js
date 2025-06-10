export const formatDate = (dateStr) => {
    if (!dateStr) return "";
    const d = new Date(dateStr);
    const gun = String(d.getDate()).padStart(2, "0");
    const ay = String(d.getMonth() + 1).padStart(2, "0");
    const yil = d.getFullYear();
    return `${gun}.${ay}.${yil}`;
}
    