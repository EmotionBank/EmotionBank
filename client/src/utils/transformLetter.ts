export const transformLetters = (content:string) => {
    return content.replace(/\n/g, '<br />').replace(/"/g, '&quot;')
}